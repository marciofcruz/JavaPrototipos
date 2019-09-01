package br.com.unip.aps.ws.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import br.com.unip.aps.ws.adapter.DateTimeAdapter;

@XmlRootElement(name = "pais")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table
public class Pais implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5530920272683174712L;

	@XmlElement(name = "id", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, insertable = true, updatable = false, unique = true)
	private Long id;

	@XmlElement(name = "nome", required = true)
	@NotEmpty
	@Size(min = 2, max = 80, message = "*O tamanho requerido é de 2 ao 80.")
	@Column(length = 80, nullable = false, insertable = true, updatable = true, unique = true)
	private String nome;

	@XmlElement(name = "sigla", required = true)
	@NotEmpty
	@Pattern(regexp = "[a-zA-Z]{2}", message = "Formato inválido!")
	@Size(min = 2, max = 2, message = "*O tamanho requerido é 2.")
	@Column(length = 2, nullable = false, insertable = true, updatable = true, unique = true)
	private String sigla;

	@XmlElement(name = "dataCriacao", required = true)
	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(nullable = false, insertable = true, updatable = false, unique = false)
	private DateTime dataCriacao;

	@XmlElement(name = "dataModificacao", required = true)
	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(nullable = true, insertable = false, updatable = true, unique = false)
	private DateTime dataModificacao;

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pais")
	private List<Endereco> enderecos = Collections.<Endereco> emptyList();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public DateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(DateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public DateTime getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(DateTime dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result
				+ ((dataModificacao == null) ? 0 : dataModificacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		Pais other = (Pais) obj;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (dataModificacao == null) {
			if (other.dataModificacao != null)
				return false;
		} else if (!dataModificacao.equals(other.dataModificacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

	@PrePersist
	public void prePersist() {
		dataCriacao = DateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		dataModificacao = DateTime.now();
	}

}
