package GestioneOrdini;

import GestioneMagazzino.Farmaco;

import java.util.Date;
import java.util.HashMap;

public class Ordine {
	private int ID_O;
	private Date dataConsegna;
	private String stato;
	private HashMap<Farmaco, Integer> farmaci;
	private String indirizzo;
	private int periodicità;
	private String dataUltimoOrdine;

	public Ordine(int ID_O, Date dataConsegna, String stato, String indirizzo) {
		this.ID_O = ID_O;
		this.dataConsegna = dataConsegna;
		this.stato = stato;
		this.indirizzo = indirizzo;
	}

	public Ordine(int ID_O, String indirizzo, int periodicità, String dataUltimoOrdine) {
		this.ID_O = ID_O;
		this.indirizzo = indirizzo;
		this.periodicità = periodicità;
		this.dataUltimoOrdine = dataUltimoOrdine;
	}

	public HashMap<Farmaco, Integer> getFarmaci() {
		return farmaci;
	}

	public void setFarmaci(HashMap<Farmaco, Integer> farmaci) {
		this.farmaci = farmaci;
	}

	public int getID_O() {
		return ID_O;
	}
	public void setID_O(int ID_O) {
		this.ID_O = ID_O;
	}
	public Date getDataConsegna() {
		return dataConsegna;
	}
	public void setDataConsegna(Date dataConsegna) {
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

	public int getPeriodicità() {
		return periodicità;
	}

	public void setPeriodicità(int periodicità) {
		this.periodicità = periodicità;
	}

	@Override
	public String toString() {
		return "Ordine{" +
				"ID_O=" + ID_O +
				", dataConsegna=" + dataConsegna +
				", stato='" + stato + '\'' +
				", farmaci=" + farmaci +
				'}';
	}
}
