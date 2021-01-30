package ir.sk.query.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/28/2021.
 */
@Entity
@Table(name = "Query", indexes = @Index(columnList = "query", name = "query_id_idx"))
public class Query implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name = "query", unique = true)
    private String query;

    @Column(name = "spelling")
    @JsonProperty("spelling")
    private String spelling;

    @OneToMany(cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="query_ID", referencedColumnName="ID")
    @JsonProperty("items")
    private List<Link> links = new ArrayList<>();

    public Query() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void add(Link link) {
        this.links.add(link);
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    @Override
    public String toString() {
        return "Query{" +
                "query='" + query + '\'' +
                '}';
    }

    public Query(Query employee) {
        this.query = employee.getSpelling();
        for (Link link: employee.getLinks()) {
            this.getLinks().add(new Link(link));
        }
    }
}
