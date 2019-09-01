package br.com.unip.aps.ws.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import br.com.unip.aps.ws.adapter.DateTimeAdapter;

@XmlRootElement(name = "ocorrencia")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table
public class Ocorrencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5272202378740191163L;

	@XmlElement(name = "id", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ocorrencia_id", nullable = false, insertable = true, updatable = false, unique = true)
	private Long id;

	@XmlElement(name = "descricao", required = true)
	@NotEmpty
	@Size(min = 10, max = 500, message = "*O tamanho requerido é de 10 ao 500.")
	@Column(length = 500, nullable = false, insertable = true, updatable = false, unique = false)
	private String descricao;

	@XmlElement(name = "nome", required = true)
	@NotEmpty
	@Size(min = 2, max = 100, message = "*O tamanho requerido é de 2 ao 100.")
	@Column(length = 100, nullable = false, insertable = true, updatable = false, unique = false)
	private String nome;

	@XmlElement(name = "dataCriacao", required = true)
	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(nullable = false, insertable = true, updatable = false, unique = false)
	private DateTime dataCriacao;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "ocorrencia", cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
	private Problema problema;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "ocorrencia", cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
	private Contato contato;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "ocorrencia", cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
	private Endereco endereco;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public DateTime getDataCriacao() {
		return dataCriacao;
	}

	public Date getDataCriacaoDate() {
		return dataCriacao.toDate();
	}

	public void setDataCriacao(DateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Problema getProblema() {
		return problema;
	}

	public void setProblema(Problema problema) {
		this.problema = problema;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
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
		Ocorrencia other = (Ocorrencia) obj;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
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

}
