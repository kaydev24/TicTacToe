package org.example.tictactoe_servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.akteure.Computer;
import org.example.akteure.Spieler;
import org.example.akteure.Strategie;
import org.example.spiel.TicTacToe;
import org.example.spiel.zugInterface;
import org.example.spielmodus.Spielmodus;

import java.io.IOException;

@WebServlet(name = Konstanten.AUSWAHL, value = "/auswahl")
public class auswahlServlet extends HttpServlet {

    private TicTacToe spiel;
    private zugInterface[] spieler = new zugInterface[2];


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        TicTacToe spiel = (TicTacToe) session.getAttribute(Konstanten.SPIEL);

        if(spiel == null) {
            spiel = new TicTacToe();
            session.setAttribute(Konstanten.SPIEL,spiel);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String startZeichen = request.getParameter(Konstanten.START_ZEICHEN);
        String spielmodus = request.getParameter(Konstanten.MODUS);
        spiel = new TicTacToe();

        System.out.println("Spielmodus: " + spielmodus);
        if (spielmodus != null) {
            if (spielmodus.equals(Spielmodus.GEGNER_VS_SPIELER.toString())) {
                spieler[0] = new Spieler(spiel, spiel.getZeichenSpieler());
                spieler[1] = new Computer(new Strategie(spiel, spiel.getZeichenGegner()));
            } else if (spielmodus.equals(Spielmodus.SPIELER_VS_SPIELER.toString())) {
                spieler[0] = new Spieler(spiel, spiel.getZeichenSpieler());
                spieler[1] = new Spieler(spiel, spiel.getZeichenGegner());
            } else if (spielmodus.equals(Spielmodus.GEGNER_VS_GEGNER.toString())) {
                spieler[0] = new Computer(new Strategie(spiel, spiel.getZeichenSpieler()));
                spieler[1] = new Computer(new Strategie(spiel, spiel.getZeichenGegner()));
            }
        }

        HttpSession session = request.getSession();
        session.setAttribute(Konstanten.SPIELER, spieler);
        session.setAttribute(Konstanten.MODUS, spielmodus);

        session.setAttribute(Konstanten.SPIEL, spiel);
        session.setAttribute(Konstanten.START_ZEICHEN, startZeichen);


        response.sendRedirect(Konstanten.TIC_TAC_TOE_JSP);
    }
}