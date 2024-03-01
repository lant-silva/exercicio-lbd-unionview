package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Viagem {
	public int codigo;
	public String onibus;
	public int motorista;
	public int horaSaida;
	public int horaChegada;
	public String partida;
	public String destino;
}
