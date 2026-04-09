# 🎬 TMDB CLI Tool (Java)

A simple command-line application written in **Java** that fetches and displays movie data from the **TMDB (The Movie Database) API**. Features both terminal output and GUI mode for browsing movies.

This project was built as a side project to practice: https://roadmap.sh/projects/tmdb-cli

* Java fundamentals
* Working with external APIs
* CLI argument parsing
* JSON processing
* GUI development with Lanterna

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
* Terminal output with text wrapping
* Simple GUI mode using Lanterna library

---

## 🛠️ Tech Stack

* **Java 17+**
* **Apache Commons CLI** – command-line argument parsing
* **Jackson** – JSON parsing
* **Lanterna** – terminal GUI
* **Maven** – build tool
* **TMDB API** – movie data

---

## 📦 Project Structure

```
.
├── pom.xml                     # Maven configuration
├── README.md                   # This file
├── tmdb_app.java               # Main entry point (with CLI parsing)
├── ApiHandler.java             # HTTP requests + API communication
├── JsonHandler.java            # JSON parsing
├── Movie.java                  # Movie data model
└── MyWindow.java               # GUI implementation
```

---

## ⚙️ Setup

### 1. Prerequisites

- **Java 17+** installed
- **Maven 3.8+** installed
- **TMDB API Key** (see step 2)

### 2. Get TMDB API Key

1. Create an account at: https://www.themoviedb.org/
2. Go to Settings → API → Create API Key
3. Copy your **API Key** (v4 auth token)

### 3. Clone and build the project

```bash
git clone <repository-url>
cd tmdb-cli-tool
mvn clean package
```

This will create a fat JAR in the `target/` directory containing all dependencies.

### 4. Set environment variable

#### macOS / Linux

```bash
export API_KEY=your_tmdb_token_here
```

#### Windows (PowerShell)

```powershell
setx API_KEY "your_tmdb_token_here"
```

---

## 💻 Usage

### Show help

```bash
java -jar target/tmdb-cli-jar-with-dependencies.jar -h
```

### Terminal mode (fetch and display movies)

```bash
# Fetch now playing movies
java -jar target/tmdb-cli-jar-with-dependencies.jar -t now_playing

# Fetch popular movies
java -jar target/tmdb-cli-jar-with-dependencies.jar -t popular

# Fetch top rated movies
java -jar target/tmdb-cli-jar-with-dependencies.jar -t top_rated

# Fetch upcoming movies
java -jar target/tmdb-cli-jar-with-dependencies.jar -t upcoming
```

### GUI mode (interactive terminal interface)

```bash
java -jar target/tmdb-cli-jar-with-dependencies.jar -g
```

This opens a Lanterna-based GUI window where you can:
- Browse movies by category
- View detailed movie information
- Navigate with mouse or keyboard
- Press ESC to close windows

### Custom page size (TODO)

```bash
# Fetch 20 movies instead of default 10
java -jar target/tmdb-cli-jar-with-dependencies.jar -t popular -p 20
```

---

## 🧠 How It Works

### 1. CLI Parsing (`tmdb_app.java`)

- Parses flags using Apache Commons CLI
- Routes to terminal or GUI mode based on flags
- Handles help, category selection, and pagination

### 2. API Request (`ApiHandler.java`)

- Builds endpoint URL with TMDB API
- Sends HTTP GET request with API key
- Handles connection timeouts and errors
- Returns raw JSON response

### 3. JSON Processing (`JsonHandler.java`)

- Parses JSON response using Jackson
- Extracts movie information
- Creates Movie objects
- Handles errors in malformed responses

### 4. Data Model (`Movie.java`)

- Contains movie properties:
    - Title
    - Release date
    - Rating
    - Overview
    - Popularity
    - Language
    - Adult content flag
- Includes validation for required fields

### 5. GUI Implementation (`MyWindow.java`)

- Uses Lanterna library for terminal GUI
- Main window with category selection
- Movie list window with clickable entries
- Movie detail window with formatted information
- Text wrapping for overviews

---

## 📄 Example Output

### Terminal Mode
```
[Fetching data for: popular]
Page size: 10
Top 10 Movies:
1. Your Heart Will Be Broken
2. Avatar: Fire and Ash
3. Hoppers
4. Shelter
5. Mike & Nick & Nick & Alice
6. Crime 101
7. The Super Mario Galaxy Movie
8. The Super Mario Bros. Movie
9. GOAT
10. Greenland 2: Migration
```

### GUI Mode
- Interactive windows with borders
- Movie details formatted in grids
- Scrollable text boxes for overviews
- Navigation with Escape key

---

## ⚠️ Notes

* The app requires a valid TMDB API token via the `API_KEY` environment variable
* Invalid category input will show help text
* The GUI mode requires a terminal that supports Lanterna (most modern terminals do)
- Error messages are displayed in the terminal for both modes

---

## 🔮 Future Improvements

* [ ] Filter by rating / year
* [ ] Cache API responses to reduce calls
* [ ] Add pagination support for large result sets
* [ ] Improve GUI with more interactive features
* [ ] Add unit tests for API and JSON handling
* [ ] Configuration file support

---

## 📜 License

This project is for educational purposes only. Please respect the TMDB API terms of use.