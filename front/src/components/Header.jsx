import React from 'react';
import {Navbar, NavbarBrand, Container} from 'reactstrap';

export default class Footer extends React.Component {
    constructor(props) {
        super(props);

    }
    render() {
        return (
            <div>
                <Navbar color="light" light expand="md">
                    <Container>
                        <NavbarBrand href="/">Sberdata</NavbarBrand>
                    </Container>
                </Navbar>
            </div>
        );
    }
}