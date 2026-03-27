# 🎬 TMDB CLI Tool (Java)

A simple command-line application written in **Java** that fetches and displays movie data from the **TMDB (The Movie Database) API**.

This project was built as a side project to practice: https://roadmap.sh/projects/tmdb-cli

* Java fundamentals
* Working with external APIs
* CLI argument parsing
* JSON processing

---

## 🚀 Features

* Fetch movies by category:

    * `now_playing`
    * `popular`
    * `top_rated`
    * `upcoming`
* Clean CLI interface using Apache Commons CLI
* JSON parsing using Jackson
* Environment-based API key handling
* Simple, readable terminal output

---

## 🛠️ Tech Stack

* **Java**
* **Apache Commons CLI** – command-line argument parsing
* **Jackson** – JSON parsing
* **TMDB API** – movie data

---

## 📦 Project Structure

```
.
├── tmdb_app.java        # Main entry point
├── cliManager.java      # CLI parsing and validation
├── apiHandler.java      # HTTP requests + API communication
├── jsonHandler.java     # JSON parsing and output formatting
```

---

## ⚙️ Setup

### 1. Clone the repository

```bash
git clone https://github.com/your-username/tmdb-cli-java.git
cd tmdb-cli-java
```

---

### 2. Get TMDB API Key

1. Create an account at: https://www.themoviedb.org/
2. Generate a **Read Access Token (v4)**

---

### 3. Set environment variable

#### macOS / Linux

```bash
export API_KEY=your_tmdb_token_here
```

#### Windows (PowerShell)

```powershell
setx API_KEY "your_tmdb_token_here"
```

---

### 4. Run the application

```bash
java tmdb_app -t popular
```

---

## 💻 Usage

### Show help

```bash
java tmdb_app -h
```

### Fetch movie categories

```bash
java tmdb_app -t now_playing
java tmdb_app -t popular
java tmdb_app -t top_rated
java tmdb_app -t upcoming
```

---

## 🧠 How It Works

### 1. CLI Parsing (`cliManager`)

* Parses flags using Apache Commons CLI
* Validates allowed categories

### 2. API Request (`apiHandler`)

* Builds endpoint URL
* Sends HTTP GET request
* Adds **Bearer token authentication**
* Returns raw JSON response

### 3. JSON Processing (`jsonHandler`)

* Extracts `results` array
* Iterates through movies
* Prints:

    * Title
    * Release date
    * Rating

---

## 📄 Example Output

```
The Super Mario Bros. Movie (2023-04-05) - Rating: 7.5
John Wick: Chapter 4 (2023-03-22) - Rating: 8.0
...
```

---

## ⚠️ Notes

* The app requires a valid TMDB API token via environment variable
* Invalid category input will throw an error
* Only basic output formatting is implemented (no pagination yet)

---