import React from 'react';
import {Link} from "react-router-dom"
import PropTypes from "prop-types";

export default class Layout extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }
    render() {
        return (
            <Link to={this.props.to} className={"btn btn-primary w-75 mt-2"}>
                {this.props.title}
            </Link>
        );
    }
}

Layout.propTypes = {
    to: PropTypes.string.isRequired,
    title: PropTypes.string.isRequired
};