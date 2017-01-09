package br.com.xyinc.modelo;

import java.io.Serializable;

/**
 * Classe do Objeto/Modelo Local a ser associado a tabela LOCAL do banco de dados 
 * @author Daniel
 *
 */
public class Local implements Serializable {
	
	private static final long serialVersionUID = -3124035664059223014L;
	
	private Long id;
    private String descricao;
    private int latitude;
    private int longitude;
    
    public Local() {
		super();
	}

	public Local(String descricao, int latitude, int longitude) {
		super();
		this.descricao = descricao;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Local(Long id, String descricao, int latitude, int longitude) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

}