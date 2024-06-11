<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="org.example.spiel.TicTacToe, org.example.spiel.Zeichen, org.example.spiel.Spielstatus, org.example.tictactoe_servlet.Konstanten" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Tic Tac Toe</title>
    <style>

        body {
            background-color: #f4f4f9;
        }

        h1 {
            text-align: center;
            color: #4A4A4A;
            margin-bottom: 50px;
            font-family: 'Arial Black';
        }

        .spielbrett {
            display: grid;
            grid-template-columns: repeat(3, 100px);
            grid-template-rows: repeat(3, 100px);
            gap: 10px;
            justify-content: center;
            margin: auto;
        }

        .feld {
            width: 100px;
            height: 100px;
            border: 2px solid #333;
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            background-color: #d3cbcb;
            padding: 0;
            margin: 0;
            box-sizing: content-box;
        }


        .button-style {
            width: 100%;
            height: 100%;
            background: none;
            border: none;
            font-size: 24px;
            cursor: pointer;
            color: #555;
            padding: 50px;
            margin: 0;
            box-sizing: border-box;
            display: block;
        }

        .content-style {
            background-color: transparent;
            width: 100%;
            height: 100%;
            text-align: center;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #a01441;
            font-family: 'Arial Black';
            font-weight: bold;
        }

        .x-color {
            color: #a01441;
        }

        .o-color {
            color: white;
            -webkit-text-stroke: 2px #a01441;
            text-stroke: 2px #a01441;
        }

        .leer-color{
            color: #d3cbcb;
        }

        .status-nachricht {
            text-align: center;
            font-size: 20px;
            color: #722f37;
            margin-bottom: 50px;
            font-family: "Arial Black";
        }

        button {
            background-color: #a01441;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            font-size: 16px;
            margin: 10px;
        }

        .menu-buttons {
            text-align: center;
            margin-top: 20px;
        }



    </style>
</head>
<body>
<h1>Tic-Tac-Toe </h1>

<%
    Spielstatus spielStatus = (Spielstatus) session.getAttribute(Konstanten.SPIELSTATUS);
%>

<div class="status-nachricht">
    <%
        if (spielStatus != null && spielStatus != Spielstatus.SPIEL_NICHT_BEENDET) {
            out.print(spielStatus.toString());
        } else {
            out.print("");
        }
    %>
</div>

<%
    TicTacToe tictactoe = (TicTacToe) session.getAttribute(Konstanten.SPIEL);
    spielStatus = tictactoe.getSpielstatus();

    if (tictactoe == null) {
        tictactoe = new TicTacToe();
        session.setAttribute(Konstanten.SPIEL, tictactoe);
    }
%>

<div class="spielbrett">
    <%
        Zeichen[][] matrix = tictactoe.getMatrix();
        String deaktiviere;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (spielStatus != Spielstatus.SPIEL_NICHT_BEENDET) {
                    deaktiviere = "disabled";
                } else {
                    deaktiviere = "";
                }
    %>

    <div class="feld">

        <form method="post" action="/spiel">
            <input type="hidden" name="posX" value="<%=i%>">
            <input type="hidden" name="posY" value="<%=j%>">
            <button type="submit" class="button-style" <%= deaktiviere %>>
                <div class="content-style
                <%
                if (matrix[i][j] == Zeichen.X) {
                    out.print("x-color");
                } else if (matrix[i][j] == Zeichen.O) {
                    out.print("o-color");
                } else {
                    out.print("leer-color");
                }
                %>">

                    <% if (matrix[i][j] != null) {
                        out.print(matrix[i][j].getDarstellung());
                    } else {
                        out.print("&nbsp;");
                    } %>

                </div>
            </button>
        </form>
    </div>

    <%
            }
        }
    %>

</div>


<div class="menu-buttons">

    <form action="/spiel" method="post">
        <input type="hidden" name="aktualisiere" value="reset">
        <button type="submit">Nochmal spielen</button>
    </form>

    <form action="/spiel" method="post">
        <input type="hidden" name="umleitung" value="menu">
        <button type="submit">Zurück zum Menü</button>
    </form>
</div>

</body>
</html>
