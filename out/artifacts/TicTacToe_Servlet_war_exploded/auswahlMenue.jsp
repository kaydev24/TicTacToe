<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Spielmodus wählen</title>
</head>
<body>
<form action="AuswahlServlet" method="POST">
    <label>Wählen Sie den Spielmodus:</label>
    <select name="modus">
        <option value="gegner_vs_spieler">Gegner vs. Spieler</option>
        <option value="spieler_vs_spieler">Spieler vs. Spieler</option>
        <option value="gegner_vs_gegner">Gegner vs. Gegner</option>
    </select>
    <br><br>
    <label>Wer soll anfangen (X oder O)?</label>
    <input type="text" name="startZeichen" required>
    <br><br>
    <input type="submit" value="Spiel starten">
</form>
</body>
</html>
