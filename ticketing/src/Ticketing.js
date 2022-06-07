import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class Ticketing extends Component {
  state = {
    name: '',
    birth: '',
    phone: ''
  }
  handleChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value
    })
  }
  handleSubmit = (e) => {
    // 페이지 리로딩 방지
    e.preventDefault();
    // 상태값을 onCreate 를 통하여 부모에게 전달
    this.props.onCreate(this.state);
    // 상태 초기화
    this.setState({
      name: '',
      birth: '',
      phone: '',
    })
  }
  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <input
          placeholder="이름"
          value={this.state.name}
          onChange={this.handleChange}
          name="name"
        />
        <br></br>
        <input
          placeholder="생년월일(ex 971121)"
          value={this.state.birth}
          onChange={this.handleChange}
          name="birth"
        />
        <br></br>
        <input
          placeholder="전화번호"
          value={this.state.phone}
          onChange={this.handleChange}
          name="phone"
        />
        <br></br>
        {/* <button type="submit">등록</button> */}
        <Link to="/seat"><button type="submit" onClick={() => this.props.history.push({
          pathname : "보낼 주소",
          state: {
            data : this.state,
          }
        })}>등록</button></Link>
      </form>
    );
  }
}

export default Ticketing;