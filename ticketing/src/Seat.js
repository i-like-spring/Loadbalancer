import React, { Component } from "react";
import ReactDOM from "react-dom";
import SeatPicker from "react-seat-picker";


class Seat extends Component {
  state = {
    loading: false
  };

  addSeatCallback = ({ row, number, id }, addCb) => {
    this.setState(
      {
        loading: true
      },
      async () => {
        await new Promise((resolve) => setTimeout(resolve, 1500));
        console.log(`Added seat ${number}, row ${row}, id ${id}`);
        const newTooltip = `tooltip for id-${id} added by callback`;
        addCb(row, number, id, newTooltip);
        this.setState({ loading: false });
      }
    );
  };

  addSeatCallbackContinousCase = (
    { row, number, id },
    addCb,
    params,
    removeCb
  ) => {
    this.setState(
      {
        loading: true
      },
      async () => {
        if (removeCb) {
          await new Promise((resolve) => setTimeout(resolve, 750));
          console.log(
            `Removed seat ${params.number}, row ${params.row}, id ${params.id}`
          );
          removeCb(params.row, params.number);
        }
        await new Promise((resolve) => setTimeout(resolve, 750));
        console.log(`Added seat ${number}, row ${row}, id ${id}`);
        const newTooltip = `tooltip for id-${id} added by callback`;
        addCb(row, number, id, newTooltip);
        this.setState({ loading: false });
      }
    );
  };

  removeSeatCallback = ({ row, number, id }, removeCb) => {
    this.setState(
      {
        loading: true
      },
      async () => {
        await new Promise((resolve) => setTimeout(resolve, 1500));
        console.log(`Removed seat ${number}, row ${row}, id ${id}`);
        // A value of null will reset the tooltip to the original while '' will hide the tooltip
        const newTooltip = ["A", "B", "C"].includes(row) ? null : "";
        removeCb(row, number, newTooltip);
        this.setState({ loading: false });
      }
    );
  };

  render() {
    const data = this.props.location.state.data;
    const shape = {
        padding: "50px",
    }
    const rows = [
      [
        { id: 1, number: 1, tooltip: "Reserved by you" },
        { id: 2, number: 2, tooltip: "Cost: 15$" },
        null,
        {
          id: 3,
          number: "3",
          orientation: "east",
          tooltip: "Reserved by Rogger"
        },
        { id: 4, number: "4", orientation: "west" },
        null,
        { id: 5, number: 5 },
        { id: 6, number: 6 }
      ],
      [
        {
          id: 7,
          number: 1,
          tooltip: "Reserved by Matthias Nadler"
        },
        { id: 8, number: 2},
        null,
        { id: 9, number: "3", orientation: "east" },
        { id: 10, number: "4", orientation: "west" },
        null,
        { id: 11, number: 5 },
        { id: 12, number: 6 }
      ],
      [
        { id: 13, number: 1 },
        { id: 14, number: 2 },
        null,
        { id: 15, number: 3, orientation: "east" },
        { id: 16, number: "4", orientation: "west" },
        null,
        { id: 17, number: 5 },
        { id: 18, number: 6 }
      ],
      [
        { id: 19, number: 1, tooltip: "Cost: 25$" },
        { id: 20, number: 2 },
        null,
        { id: 21, number: 3, orientation: "east" },
        { id: 22, number: "4", orientation: "west" },
        null,
        { id: 23, number: 5 },
        { id: 24, number: 6 }
      ],
      [
        { id: 25, number: 1},
        { id: 26, number: 2, orientation: "east" },
        null,
        { id: 27, number: "3"},
        { id: 28, number: "4", orientation: "west" },
        null,
        { id: 29, number: 5, tooltip: "Cost: 11$" },
        { id: 30, number: 6 }
      ]
    ];
    const { loading } = this.state;
    return (
      <div>
          <center>
          
        <h1>Please Select Your Seats:</h1>
        <div style={{ marginTop: "100px" }}>
          <SeatPicker
            addSeatCallback={this.addSeatCallback}
            removeSeatCallback={this.removeSeatCallback}
            rows={rows}
            maxReservableSeats={1}
            alpha
            visible
            selectedByDefault
            loading={loading}
            tooltipProps={{ multiline: true }}
          />
        </div>

        {/* <div style={{ marginTop: "5px" }}>
          <SeatPicker
            addSeatCallback={this.addSeatCallbackContinousCase}
            removeSeatCallback={this.removeSeatCallback}
            rows={rows}
            maxReservableSeats={3}
            alpha
            visible
            selectedByDefault
            loading={loading}  
            tooltipProps={{ multiline: true }}
            continuous
          />
        </div> */}
        <div style={shape}></div>
        <button>DONE</button>
        </center>
      </div>
    );
  }
}


const rootElement = document.getElementById("root");
ReactDOM.render(<Seat />, rootElement);



export default Seat;