import React, { Component } from 'react'
import Display from './Display'

export default class App extends Component {

 state = {
   name : "Kiran",
   mail : "gk35133@citi.com"
 }


  render() {


    return (
     

        <div>
        <center>
          <h1>Name from App JS file {this.state.name}</h1>
          <h1>Name from App JS file {this.state.mail}</h1>
          <Display name={this.state.name} mail={this.state.mail}></Display>
        </center>
      </div>

     
    )
  }
}
