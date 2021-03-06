
package com.example.paulo_000.readerdiary;

import com.example.paulo_000.readerdiary.LivroCursor.Factory;

import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;
import io.objectbox.relation.RelationInfo;
import io.objectbox.relation.ToOne;
import io.objectbox.internal.ToOneGetter;


// THIS CODE IS GENERATED BY ObjectBox, DO NOT EDIT.

/**
 * Properties for entity "Livro". Can be used for QueryBuilder and for referencing DB names.
 */
public final class Livro_ implements EntityInfo<Livro> {

    // Leading underscores for static constants to avoid naming conflicts with property names

    public static final String __ENTITY_NAME = "Livro";

    public static final int __ENTITY_ID = 3;

    public static final Class<Livro> __ENTITY_CLASS = Livro.class;

    public static final String __DB_NAME = "Livro";

    public static final CursorFactory<Livro> __CURSOR_FACTORY = new Factory();

    @Internal
    static final LivroIdGetter __ID_GETTER = new LivroIdGetter();

    public final static Property id = new Property(0, 1, long.class, "id", true, "id");
    public final static Property titulo = new Property(1, 2, String.class, "titulo");
    public final static Property autor = new Property(2, 3, String.class, "autor");
    public final static Property genero = new Property(3, 4, String.class, "genero");
    public final static Property ano = new Property(4, 5, String.class, "ano");
    public final static Property qtdPg = new Property(5, 6, String.class, "qtdPg");
    public final static Property pgAtual = new Property(6, 7, String.class, "pgAtual");
    public final static Property status = new Property(7, 8, String.class, "status");
    public final static Property dataInicial = new Property(8, 9, String.class, "dataInicial");
    public final static Property dataFinal = new Property(9, 10, String.class, "dataFinal");
    public final static Property donoId = new Property(10, 11, long.class, "donoId");

    public final static Property[] __ALL_PROPERTIES = {
        id,
        titulo,
        autor,
        genero,
        ano,
        qtdPg,
        pgAtual,
        status,
        dataInicial,
        dataFinal,
        donoId
    };

    public final static Property __ID_PROPERTY = id;

    public final static Livro_ __INSTANCE = new Livro_();

    @Override
    public String getEntityName() {
        return __ENTITY_NAME;
    }

    @Override
    public int getEntityId() {
        return __ENTITY_ID;
    }

    @Override
    public Class<Livro> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override
    public String getDbName() {
        return __DB_NAME;
    }

    @Override
    public Property[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override
    public Property getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override
    public IdGetter<Livro> getIdGetter() {
        return __ID_GETTER;
    }

    @Override
    public CursorFactory<Livro> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    @Internal
    static final class LivroIdGetter implements IdGetter<Livro> {
        @Override
        public long getId(Livro object) {
            return object.getId();
        }
    }

    /** To-one relation "dono" to target entity "Usuario". */
    public static final RelationInfo<Usuario> dono =
            new RelationInfo<>(Livro_.__INSTANCE, Usuario_.__INSTANCE, null, new ToOneGetter<Livro>() {
                @Override
                public ToOne<Usuario> getToOne(Livro entity) {
                    return entity.getDono();
                }
            });

}
