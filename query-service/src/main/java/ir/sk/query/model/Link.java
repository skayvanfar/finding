package ir.sk.query.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import estivate.annotations.Select;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/28/2021.
 */
@Entity
@Table(name = "Link")
public class Link {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;

    @Column(name = "title")
    @JsonProperty("title")
    private String title;

    @Column(name = "url", length = 4000)
    @JsonProperty("link")
    private String url;

    @Column(name = "snippet", length = 4000)
    @JsonProperty("snippet")
    private String snippet;

    public Link() {
    }

    public Link(String title, String url, String snippet) {
        this.title = title;
        this.url = url;
        this.snippet = snippet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(title, link.title) &&
                Objects.equals(url, link.url) &&
                Objects.equals(snippet, link.snippet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url, snippet);
    }

    @Override
    public String toString() {
        return "Link{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", snippet='" + snippet + '\'' +
                '}';
    }

    public Link(Link link) {
        this.title = link.title;
        this.url = link.url;
        this.snippet = link.title;
    }
}
