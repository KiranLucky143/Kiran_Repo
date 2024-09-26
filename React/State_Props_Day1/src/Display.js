import React, { Component } from 'react'

export default class Display extends Component {
  render() {
    return (
      <div>
    {/* we have to get values from state object //it is like getting values from parent using props attribute  */}

    {/* we can use the values declared in the Apps only if we declare using state keyword */}
    
        <h1>Name is {this.props.name}</h1>
        <h1>Mail_Id is {this.props.mail}</h1>
      </div>
    )
  }
}


