package org.example.tictactoe_servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.akteure.Computer;
import org.example.spiel.*;

import java.io.IOException;

@WebServlet(name = Konstanten.SPIEL, value = "/spiel")
public class spielServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        TicTacToe spiel = (TicTacToe) session.getAttribute(Konstanten.SPIEL);

        String umleitung = request.getParameter(Konstanten.UMLEITUNG);
        String aktualisiere = request.getParameter(Konstanten.AKTUALISIERE);

        Object obj = session.getAttribute(Konstanten.START_ZEICHEN);
        char ch = obj.toString().charAt(0);
        Zeichen startZeichen = Zeichen.getFromChar(ch);

        if (Konstanten.RESET.equals(aktualisiere)) {
            spiel.initialisiereMatrix();
            Zeichen behalteStartZeichen = Zeichen.getFromChar(((String) session.getAttribute(Konstanten.START_ZEICHEN)).charAt(0));
            Integer aktuellerSpieler = behalteStartZeichen.getWert();
            session.setAttribute(Konstanten.SPIEL, spiel);
            session.setAttribute(Konstanten.AKTUELLER_SPIELER, aktuellerSpieler);
            response.sendRedirect(request.getContextPath() + Konstanten.TIC_TAC_TOE_JSP);
            return;
        }

        if (Konstanten.MENU.equals(umleitung)) {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + Konstanten.AUSWAHL_MENUE_JSP);
            return;
        }

        if (startZeichen != null && spiel != null) {
            int posX = Integer.parseInt(request.getParameter(Konstanten.POS_X));
            int posY = Integer.parseInt(request.getParameter(Konstanten.POS_Y));

            zugInterface[] spieler = (zugInterface[]) session.getAttribute(Konstanten.SPIELER);

            Integer aktuellerSpieler = (Integer) session.getAttribute(Konstanten.AKTUELLER_SPIELER);
            if (aktuellerSpieler == null) {
                aktuellerSpieler = startZeichen.getWert();
            }

            if (spiel.isLeer(posX, posY)) {
                spieler[aktuellerSpieler].setzeZeichen(posX, posY);
                session.setAttribute(Konstanten.SPIEL, spiel);

                Spielstatus status = spiel.getSpielstatus();
                session.setAttribute(Konstanten.SPIELSTATUS, status);
                if (status != Spielstatus.SPIEL_NICHT_BEENDET) {
                    request.getRequestDispatcher(Konstanten.TIC_TAC_TOE_JSP).forward(request, response);
                    return;
                }

                aktuellerSpieler = 1 - aktuellerSpieler;
                if (spieler[aktuellerSpieler] instanceof Computer) {
                    spieler[aktuellerSpieler].setzeZeichen(posX, posY);
                    session.setAttribute(Konstanten.SPIEL, spiel);
                } else {
                    session.setAttribute(Konstanten.AKTUELLER_SPIELER, aktuellerSpieler);
                }

                status = spiel.getSpielstatus();
                session.setAttribute(Konstanten.SPIELSTATUS, status);
                if (status != Spielstatus.SPIEL_NICHT_BEENDET) {
                    request.getRequestDispatcher(Konstanten.TIC_TAC_TOE_JSP).forward(request, response);
                    return;
                }

                response.sendRedirect(request.getContextPath() + Konstanten.TIC_TAC_TOE_JSP);
                return;
            }
        }
        response.sendRedirect(request.getContextPath() + Konstanten.TIC_TAC_TOE_JSP);
    }
}
