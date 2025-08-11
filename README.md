# CardThreadSimulation — JavaFX Card Game Simulation

This is a JavaFX application that simulates a small board game with cards, pieces (figures), and simple real-time events. Players take turns drawing cards and moving their top piece along a precomputed path on a square grid. Diamonds can appear on the board and speed up pieces; holes can remove pieces. The UI shows the board, the active player, the current card, and a small log/list.

This is a educational project that demonstrates JavaFX UI, basic simulation/threads, and object-oriented design (players, figures, cards, rules).
## What the game does:

- Builds a square board (DIMENZIJA_MATRICE, default 7×7) and a movement path that winds through the grid.

- Creates a deck:
  - Number cards (card_1.png to card_4.png) that move a piece by 1–4 steps.
  - A special card type exists in code (SpecijalnaKarta), currently scaffolded (activation commented out).
- Creates players (Igrac) with a stack of pieces (Figura):
  - Obicna – normal piece.
  - Superbrza – moves faster (number card ×2 plus its current speed bonus).
  - Lebdeca – “floating” piece meant to be unaffected by holes.
- Runs a turn loop:
  1. Take the next player from the queue.
  2. If the player’s top piece is in a hole, it’s removed; if the player has no pieces left, the player is dropped from the game.
  3. Draw a card from the deck; show it in the UI.
  4. Move the piece along the path by the card’s amount (with type-specific bonuses).
  5. If the piece lands on a diamond, it gains speed for future moves.
  6. Put the player back into the queue and continue.
- Spawns diamonds asynchronously: a background thread (Duh) periodically places “dijamant” markers on random path cells; picking one increases a piece’s brzina (speed).
- Holes are supported in the model (via SpecijalnaKarta) and can eliminate pieces; the “floating” piece (Lebdeca) is intended to safely cross them. Activating the special card is present but commented out in the current simulation loop.
  
## Key packages and classes

- com.example.card-threads-simulation.HelloApplication
JavaFX entry point (loads FXML, shows the main window).

- com.example.kontroleri.MainController - Main UI controller:
  - Builds/updates the board grid (GridPane) with numbered cells.
  - Displays the current player, the current card image, and a list/log.
  - Provides helper methods used by the simulation to color cells, place/remove pieces, and render diamonds/holes.

- com.example.simulacija.Simulacija
  The core simulation (extends Thread):
  - matrica: the board state (grid of objects/markers such as figures, “dijamant”, “rupa”, etc.).
  - putanja: the ordered list of coordinates the pieces follow (precomputed path).
  - igraci: a queue of players; spil: a queue (deck) of cards.
  - The run() method implements the turn loop and card effects.

- com.example.igrac.Igrac
Player model (name/color + Stack<Figura> pieces).
- com.example.figura.*
  - Figura: base class (sprite image, color, current path index, speed, “in hole” flag, movement).
  - Obicna, Superbrza, Lebdeca: concrete piece types with small rule differences.
- com.example.karta.*
  - Karta: base card with an image.
  - ObicnaKarta: number card; getBrojKarte() returns 1–4.
  - SpecijalnaKarta: special card logic (creates/clears holes on the path; activation is stubbed out in Simulacija.run()).

- com.example.konstante.Konstante
Central configuration: board size, number of players, colors, symbols, and constants such as:
- DIMENZIJA_MATRICE (default 7)
- BROJ_IGRACA (default 1)
- Board/piece colors and icon strings
- Card counts: BROJ_OBICNIH_KARATA (10 for each of 1–4), BROJ_SPECIJALNIH_KARATA (present for future use)

## UI overview
FXML files in src/main/resources/com/example/card-threads-simulation/ define the screens:
- hello-view.fxml — main window:
  - board grid
  - current card image
  - current player label
  - start/pause button (Pokreni / Zaustavi)
  - a ListView used as a simple log/inspector

- intro-window.fxml, listview-window.fxml — auxiliary windows/controllers used for small popups/inspections.

Card artwork lives in cards/:
- card_1.png, card_2.png, card_3.png, card_4.png, card_joker.png.
> [!NOTE]
> Images are loaded using new File("cards/card_X.png"), which means the working directory when you run the app must be the project folder that contains cards/.

## How to run

This project is configured with Maven and the JavaFX Maven Plugin.
Requirements:
- JDK 17 (recommended, matches JavaFX 17.0.2 in the POM)
- Maven 3.8+ (or use the provided wrapper)

**Run with Maven wrapper (recommended)**

From the card-threads-simulation/ folder:
```
# Windows
mvnw.cmd clean javafx:run

# macOS/Linux
./mvnw clean javafx:run
```

### Run from an IDE
- Open the Maven project (pom.xml).
- Set HelloApplication as the main class if needed.
- Ensure the working directory is the card-threads-simulation/ folder so images under cards/ resolve.

### Configuration you can tweak
Edit com.example.konstante.Konstante:
- DIMENZIJA_MATRICE — board size (odd numbers work best with the current path logic).
- BROJ_IGRACA — number of players to enqueue.
- Colors/symbols if you want to change the board styling.

Gameplay toggles (in code):
- Special card activation is present but commented in Simulacija.run(); you can enable the call to ((SpecijalnaKarta) trenutnaKarta).aktiviraj(); to try hole creation/cleanup.
- The “ghost” thread (Duh) periodically drops diamonds; you can change timing or counts in that class.

## How it works (short technical walkthrough)
1. Setup (Simulacija constructor):
   - Initialize matrica (grid), build the deck (spil) with numeric cards (and place for special cards), build the player queue (igraci), and compute the movement path (putanja) over the grid.
2. Turn loop (run()):
   - Poll the next player. If their top piece is in a hole, remove that piece. If the player has no pieces, drop the player and continue.
   - Draw a card, re-queue it to the bottom of the deck, and update the UI with the card image.
   - Move the piece along putanja:
     - Superbrza gets a higher step count (twice the card value plus its current brzina).
     - Others move by the card’s number.
     - Landing on a diamond increases brzina (a permanent speed bonus for that piece).
   - Push the player to the back of the queue.
3. Async events:
   - Duh (a background thread) periodically marks grid cells as diamonds; they appear and are picked up during movement.
   - Special card logic (holes) is implemented in SpecijalnaKarta and uses stacks to remember and later restore affected cells; call is currently commented out.
4. Finish:
   - The loop ends when igraci becomes empty (all players lost all pieces) or when paused/stopped from the UI.

### Known limitations / notes

* The special card effect is present but not enabled by default in the turn loop.
* Image loading uses file paths relative to the working directory; run from card-threads-simulation/ or adjust to use classpath resources.
* Board path generation is tailored to odd dimensions; if you change DIMENZIJA_MATRICE, ensure the path still covers the board as expected.        
