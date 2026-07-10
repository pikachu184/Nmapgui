import { useState } from "react";
import "./App.css";

function App() {

  const [target, setTarget] = useState("");
  const [scanType, setScanType] = useState("-sn");
  const [result, setResult] = useState("");
  const [loading, setLoading] = useState(false);

  const scanTarget = async () => {

    if (!target.trim()) {
      alert("Enter IP or Domain");
      return;
    }

    setLoading(true);
    setResult("");

    try {

      const response = await fetch(
        `http://localhost:8080/api/scan?target=${target}&scanType=${scanType}`
      );

      const data = await response.text();

      setResult(data);

    } catch (error) {

      setResult(
        "Error : " + error.message
      );

    } finally {

      setLoading(false);
    }
  };

  return (
    <div className="container">

      <h1>Nmap Scanner</h1>

      <div className="controls">

        <select
          value={scanType}
          onChange={(e) =>
            setScanType(e.target.value)
          }
        >
          <option value="-sn">
            Ping Scan
          </option>

          <option value="-sV">
            Service Detection
          </option>

          <option value="-F">
            Fast Scan
          </option>
        </select>

        <input
          type="text"
          placeholder="Enter IP or Domain"
          value={target}
          onChange={(e) =>
            setTarget(e.target.value)
          }
        />

        <button onClick={scanTarget}>
          Scan
        </button>

      </div>

      {loading && (
        <p>Scanning...</p>
      )}

      <pre className="result">
        {result}
      </pre>

    </div>
  );
}

export default App;