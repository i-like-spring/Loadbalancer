import React, { Component } from 'react';
import './App.css';

class Clockcmp extends Component {
    constructor(props){
        super(props)
        this.state = {d: new Date()}
    }
    componentDidMount() { // Clockcmp 컴포넌트가 불러올때마다 1초씩 this.Change()를 부른다 
        this.timeID = setInterval(
            () => this.Change(),
            1000
        )
    }

    componentWillUnmount(){ //종료되면 반복하는것도 클리어시키기
        clearInterval(this.timeID)
    }

    Change = () => {  //시계 구현​
        this.setState({
            d : new Date(),
        })
    }

    render() {
        return(
            <div>
                {this.state.d.getHours()}:{this.state.d.getMinutes()}:          {this.state.d.getSeconds()}
            </div>)
    }
}

export default Clockcmp;
