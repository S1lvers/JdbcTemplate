import React from 'react';
import {Col, Container, FormGroup} from "reactstrap";
import LinkButton from "./LinkButton";

export default class Layout extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }
    render() {
        return (
            <div className={"layout"}>
                <Container className={"py-4"}>
                    <FormGroup row>
                        <Col md={4} className={"text-center"}>
                            <LinkButton to={"/dbAppender"} title={"Записать данные"}/>
                        </Col>
                        <Col md={2}>

                        </Col>
                        <Col md={5} className={"text-center"}>
                            <LinkButton to={"/"} title={"Скоро тут будет загрузка файла"}/>
                        </Col>
                    </FormGroup>
                </Container>
            </div>
        );
    }
}