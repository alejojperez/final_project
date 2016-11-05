/**
 * Created by Alejandro Perez on 11/3/16
 * github page: https://github.com/alejojperez
 */
package com.github.alejojperez.library_project.modules.ui.scopes;

import com.github.alejojperez.library_project.modules.data.entities.Book;
import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MasterDetailScope implements Scope
{
    private ObjectProperty<Book> selectedBook;

    MasterDetailScope() {
        this.selectedBook = new SimpleObjectProperty<>();
        this.selectedBook.setValue(new Book());
    }

    public ObjectProperty<Book> selectedBookProperty() {
        return selectedBook;
    }
}
