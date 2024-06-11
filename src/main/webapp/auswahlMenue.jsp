<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="org.example.spielmodus.Spielmodus" %>
<%@ page import="org.example.spiel.Zeichen" %>
<!DOCTYPE html>
<html>
<head>
    <title>Spielmodus wählen</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 20px;
        }
        form {
            background-color: white;
            padding: 20px;
            width: 300px;
            margin: auto;
        }
        label {
            font-size: 14px;
            color: #555;
        }
        select {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            margin-bottom: 20px;
            border-radius: 4px;
            border: 1px solid #ddd;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #a01441;
            border: none;
            border-radius: 1px;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }

    </style>
</head>
<body>
<form action="/auswahl" method="POST">
    <label>Wählen Sie den Spielmodus:</label>
    <select name="modus">
        <% for (Spielmodus modus : Spielmodus.values()) { %>
        <option value="<%= modus.toString() %>"><%= modus.toString() %></option>
        <% } %>
    </select>
    <br>
    <label>Wer soll anfangen (X oder O)?</label>
    <select name="startZeichen">
        <% for (Zeichen zeichen : Zeichen.values()) {
            if (!zeichen.name().equals("I")) {
        %>
        <option value="<%= zeichen.getDarstellung() %>"><%= zeichen.name() %></option>
        <%
                }
            } %>
    </select>
    <br>
    <input type="submit" value="Spiel starten">
</form>
</body>
</html>
