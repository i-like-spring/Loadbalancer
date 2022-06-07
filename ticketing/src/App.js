import React, { Component } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./Header";
import Main from "./Main";
import Ticketing from "./Ticketing";
import NotFound from "./NotFound";
import Clockcmp from "./Clockcmp";
import Seat from "./Seat";

class App extends Component {
  handleCreate = (data) => {
    console.log(data);
  };
  render() {
    return (
      <div className="App">
        <Clockcmp></Clockcmp>
        <BrowserRouter>
          <Header/>
          <Routes>
            <Route path="/" element={<Main />}></Route>
            <Route
              path="/ticketing/*"
              element={<Ticketing onCreate={this.handleCreate} />}
            ></Route>
            {/* 상단에 위치하는 라우트들의 규칙을 모두 확인, 일치하는 라우트가 없는경우 처리 */}
            <Route path="/seat/*" element={<Seat></Seat>}></Route>
            <Route path="*" element={<NotFound />}></Route>
          </Routes>
        </BrowserRouter>
      </div>
    );
  }
}

export default App;