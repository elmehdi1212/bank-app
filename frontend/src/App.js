import React, { useState } from "react";
import "./App.css";

function App() {
  const [amount, setAmount] = useState(0);
  const [statement, setStatement] = useState("");

  const handleDeposit = async () => {
    await fetch(`http://localhost:8080/api/account/deposit?amount=${amount}`, {
      method: "POST",
    });
    setAmount(0);
  };

  const handleWithdraw = async () => {
    await fetch(`http://localhost:8080/api/account/withdraw?amount=${amount}`, {
      method: "POST",
    });
    setAmount(0);
  };

  const handlePrintStatement = async () => {
    const response = await fetch("http://localhost:8080/api/account/statement");
    const data = await response.text();
    setStatement(data);
  };

  return (
    <div className="App">
      <h1>Bank Account</h1>
      <div>
        <input
          type="number"
          value={amount}
          onChange={(e) => setAmount(parseInt(e.target.value))}
          placeholder="Enter amount"
        />
        <button onClick={handleDeposit}>Deposit</button>
        <button onClick={handleWithdraw}>Withdraw</button>
      </div>
      <div>
        <button onClick={handlePrintStatement}>Print Statement</button>
      </div>
      <pre>{statement}</pre>
    </div>
  );
}

export default App;
