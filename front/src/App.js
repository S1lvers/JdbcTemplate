import React, {Component} from "react"
import "react-notifications-component/dist/theme.css";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom"
import "./App.css"
import Header from "./components/Header"

import axios from "axios"
import Footer from "./components/Footer";
import Layout from "./components/Layout";
import DbAppender from "./components/DbAppender";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {};
    }

    componentDidMount() {
    }

    render() {
        return (
            <div className="App">
                <Header/>
                <Router>
                    <div>
                        <Route exact path={"/"} component={Layout}/>
                        <Route exact path={"/dbAppender"} component={DbAppender} />
                    </div>
                </Router>
                <Footer/>
            </div>
        );
    }

    toggle(_error) {
        this.setState({
            error: _error,
        });
    }

    closeModal = () => {
        this.setState({
            error: null
        })
    }

    startErrorHandling() {

        axios.interceptors.request.use(function (config) {
            config.headers = {Pragma: 'no-cache'};
            return config;
        }, function (error) {
            return Promise.reject(error);
        });


        let func = this.toggle;

        axios.interceptors.response.use(null, function (error) {
            if (!error.response) {
                console.error("Ошибка при обработке запроса:");
                console.dir(error);
                return Promise.reject(error);
            }

            if (error.response.status === 401) {
                window.location = "/";
            } else if (error.response.status !== 403) {
                func(error.response)
            }

            return Promise.reject(error);
        });
    }


}

export default App;
