import 'core-js/es7';
import 'core-js/es6';
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import {unregister} from './registerServiceWorker';

ReactDOM.render(<App/>, document.getElementById('root'));
unregister();