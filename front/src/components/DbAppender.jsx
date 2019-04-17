import React from 'react';
import {Col, Container, FormGroup, Input, Label, Button} from "reactstrap";
import axios from "axios";
import config from "../config/config"


export default class DbAppender extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            schemas: [],
            tables: [],
            schema: "",
            table: "",
            data: ""
        };
    }

    componentDidMount() {
        axios.get(config.urls.schemas)
            .then(response => {
                const data = response.data;
                this.setState({
                    schemas: data
                });
            })
    }

    onSchemaChange = (e) => {
        const schemaName = e.target.value;
        if (!schemaName) this.setState({table: ""});
        this.setState({schema : schemaName});
        this.getTables(schemaName);
    };

    onTableChange = (e) => {
        const tableName = e.target.value;
        this.setState({table : tableName});
    };

    getTables = (schemaName) => {
        axios.get(config.urls.tables.replace("{schemaName}", schemaName))
            .then(response => {
                const data = response.data;
                this.setState({
                    tables: data,
                });
            })
    };

    onChangeData = (e) => {
        this.setState({data: e.target.value})
    };


    onUpdate = (e) => {
        e.preventDefault();
        let data = JSON.parse(this.state.data);
        console.log(this.state)
        axios.post(config.urls.append, {
            schemaName: this.state.schema,
            tableName: this.state.table,
            data: data
        }).then((response) => {
            console.log(response.data);
        })
    };


    render() {
        return (
            <div className={"dbAppender"}>
                <Container className={"py-4"}>
                    <div className="row">
                        {/*<div className="col-sm"></div>*/}
                        <div className="col-xl-6 offset-xl-3 col-md-8 offset-md-2 col-sm-10 offset-sm-1">
                            <form className="mt-4">
                                <FormGroup>
                                    <Label for="selectSchema">Схема: </Label>
                                    <Input type="select" name="selectSchema" id="selectSchema" onChange={this.onSchemaChange}>
                                        <option></option>
                                        {this.state.schemas.map(option => {
                                            return (
                                                <option key={option} value={option}>
                                                    {option}
                                                </option>
                                            );
                                        })}
                                    </Input>
                                </FormGroup>
                                <FormGroup hidden={!this.state.schema}>
                                    <Label for="selectTable">Таблица: </Label>
                                    <Input type="select" name="selectTable" id="selectTable" onChange={this.onTableChange}>
                                        <option></option>
                                        {this.state.tables.map(option => {
                                            return (
                                                <option key={option} value={option}>
                                                    {option}
                                                </option>
                                            );
                                        })}
                                    </Input>
                                </FormGroup>
                                <FormGroup hidden={!this.state.table}>
                                    <Label for="data">Данные для обновления таблицы {this.state.table}</Label>
                                    <Input type="textarea" name="data" id="data" onChange={this.onChangeData} value={this.state.data}/>
                                </FormGroup>
                                <Button type="submit" color="primary" hidden={!this.state.table}
                                        onClick={this.onUpdate}>Сделать запись!</Button>
                            </form>
                        </div>
                    </div>
                </Container>
            </div>
        );
    }
}