package GestioneOrdini;

import GestioneMagazzino.Farmaco;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Ordine {
	private int ID_O;
	private String dataConsegna;
	private String stato;
	private ArrayList<Farmaco> farmaci;
	private String indirizzo;
	private int periodicita;
	private String dataUltimoOrdine;

	public Ordine(int ID_O, String dataConsegna, String stato, String indirizzo) {
		this.ID_O = ID_O;
		this.dataConsegna = dataConsegna;
		this.stato = stato;
		this.indirizzo = indirizzo;
	}

	public Ordine(int ID_O, String indirizzo, int periodicita, String dataUltimoOrdine) {
		this.ID_O = ID_O;
		this.indirizzo = indirizzo;
		this.periodicita = periodicita;
		this.dataUltimoOrdine = dataUltimoOrdine;
	}

	public ArrayList<Farmaco> getFarmaci() {
		return farmaci;
	}

	public void setFarmaci(ArrayList<Farmaco> farmaci) {
		this.farmaci = farmaci;
	}

	public int getID_O() {
		return ID_O;
	}
	public void setID_O(int ID_O) {
		this.ID_O = ID_O;
	}
	public String getDataConsegna() {
		return dataConsegna;
	}
	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna = dataConsegna;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public int getPeriodicita() {
		return periodicita;
	}

	public void setPeriodicita(int periodicita) {
		this.periodicita = periodicita;
	}

	@Override
	public String toString() {
		return "Ordine{" +
				"ID_O=" + ID_O +
				", dataConsegna=" + dataConsegna +
				", stato='" + stato + '\'' +
				'}';
	}
}
