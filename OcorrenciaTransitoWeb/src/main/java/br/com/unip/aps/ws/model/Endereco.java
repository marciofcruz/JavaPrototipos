package br.com.unip.aps.ws.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "endereco")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 193254376328303725L;

	@XmlElement(name = "id", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, insertable = true, updatable = false, unique = true)
	private Long id;

	@XmlElement(name = "pais", required = true)
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "pais", nullable = false, insertable = true, updatable = true, unique = false)
	private Pais pais = new Pais();

	@XmlElement(name = "estado", required = true)
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "estado", nullable = false, insertable = true, updatable = true, unique = false)
	private Estado estado = new Estado();

	@XmlElement(name = "cidade", required = true)
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "cidade", nullable = false, insertable = true, updatable = true, unique = false)
	private Cidade cidade = new Cidade();

	@XmlElement(name = "bairro", required = true)
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "bairro", nullable = false, insertable = true, updatable = true, unique = false)
	private Bairro bairro = new Bairro();

	@XmlElement(name = "logradouro", required = true)
	@ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "logradouro", nullable = false, insertable = true, updatable = true, unique = false)
	private Logradouro logradouro = new Logradouro();

	@XmlTransient
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ocorrencia_id", nullable = false, insertable = true, updatable = false, unique = false)
	private Ocorrencia ocorrencia = new Ocorrencia();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		return true;
	}

}
