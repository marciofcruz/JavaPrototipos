package br.com.unip.aps.ws.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

@XmlRootElement(name = "problema")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table
public class Problema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3191002908800479635L;

	@XmlElement(name = "id", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "problema_id", nullable = false, insertable = true, updatable = false, unique = true)
	private Long id;

	@XmlElement(name = "nome", required = true)
	@NotEmpty
	@Size(min = 2, max = 80, message = "*O tamanho requerido é de 2 ao 80.")
	@Column(length = 80, nullable = false, insertable = true, updatable = true, unique = false)
	private String nome;

	@XmlElement(name = "descricao", required = true)
	@NotEmpty
	@Size(min = 10, max = 150, message = "*O tamanho requerido é de 10 ao 150.")
	@Column(length = 150, nullable = false, insertable = true, updatable = true, unique = false)
	private String descricao;

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
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ocorrencia_id", nullable = false, insertable = true, updatable = false, unique = false)
	private Ocorrencia ocorrencia = new Ocorrencia();

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		result = prime * result
				+ ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result
				+ ((dataModificacao == null) ? 0 : dataModificacao.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Problema other = (Problema) obj;
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
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
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
