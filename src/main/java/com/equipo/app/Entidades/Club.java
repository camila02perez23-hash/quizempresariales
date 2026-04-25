package com.equipo.app.Entidades;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document (collection = "club")
public class Club {

	@Id
	private String id;
	private String nombre;
	private String ubicacion;
	private String estadio;
	private int anioFundacion;
	private int numeroJugadores;
	
	@DBRef 
	private Asociacion asociacion;
	
	@DocumentReference
    private List<Jugador> jugadores;
	
	@DocumentReference 
	private Entrenador entrenador;
	
	@DocumentReference
    private List<Competicion> competiciones;
	
	public Club() {}
	
	public Club(String nombre, String ubicacion, String estadio,
            int anioFundacion, int numeroJugadores,
            Entrenador entrenador, List<Jugador> jugadores,
            Asociacion asociacion, List<Competicion> competiciones) {
     this.nombre = nombre;
     this.ubicacion = ubicacion;
     this.estadio = estadio;
     this.anioFundacion = anioFundacion;
     this.numeroJugadores = numeroJugadores;
     this.entrenador = entrenador;
     this.jugadores = jugadores;
     this.asociacion = asociacion;
     this.competiciones = competiciones;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getEstadio() {
		return estadio;
	}

	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}

	public int getAnioFundacion() {
		return anioFundacion;
	}

	public void setAnioFundacion(int anioFundacion) {
		this.anioFundacion = anioFundacion;
	}

	public int getNumeroJugadores() {
		return numeroJugadores;
	}

	public void setNumeroJugadores(int numeroJugadores) {
		this.numeroJugadores = numeroJugadores;
	}

	public Asociacion getAsociacion() {
		return asociacion;
	}

	public void setAsociacion(Asociacion asociacion) {
		this.asociacion = asociacion;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public List<Competicion> getCompeticiones() {
		return competiciones;
	}

	public void setCompeticiones(List<Competicion> competiciones) {
		this.competiciones = competiciones;
	}	
	
}
