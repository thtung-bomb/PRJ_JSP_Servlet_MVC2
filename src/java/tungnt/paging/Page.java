/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.paging;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Thanh Tung
 */
public class Page<T> implements Serializable {

    private List<T> content;
    private int numberOfPages;

    public Page() {
    }

    public Page(List<T> content, int numberOfPages) {
        this.content = content;
        this.numberOfPages = numberOfPages;
    }

    /**
     * @return the content
     */
    public List<T> getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(List<T> content) {
        this.content = content;
    }

    /**
     * @return the numberOfPages
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * @param numberOfPages the numberOfPages to set
     */
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

}
