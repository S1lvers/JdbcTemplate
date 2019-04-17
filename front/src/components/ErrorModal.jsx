import React, {Component} from 'react';
import {Alert, Button, ListGroupItem, Modal, ModalBody, ModalFooter, ModalHeader} from "reactstrap"

export default class ErrorModal extends Component {

    constructor(props) {
        super(props);

        this.state = {
            error: props.error
        }
    }

    shouldComponentUpdate(nextProps, nextState) {
        if (nextState.error !== nextProps.error) {
            this.setState({
                error: nextProps.error,
            });
            return false;
        }
        return true;
    }


    render() {
        return (
            this.state.error ?
                <Modal isOpen={this.state.error} className={this.props.className}>
                    <ModalHeader>Произошла ошибка</ModalHeader>
                    <ModalBody>
                        <Alert color="danger">
                            {this.state.error.data.error.message}
                        </Alert>
                        {
                            this.state.error.data.error.fields
                                ? this.state.error.data.error.fields.map(field => {
                                    return <ListGroupItem color="warning">{field.error}</ListGroupItem>
                                })
                                : ""
                        }
                    </ModalBody>
                    <ModalFooter>
                        <Button color="primary" onClick={this.props.closeModal}>Закрыть</Button>
                    </ModalFooter>
                </Modal>
                :
                null
        );
    }


}

