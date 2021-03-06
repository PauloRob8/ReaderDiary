package com.example.paulo_000.readerdiary;


import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.relation.ToOne;
// THIS CODE IS GENERATED BY ObjectBox, DO NOT EDIT.

/**
 * ObjectBox generated Cursor implementation for "Capitulos".
 * Note that this is a low-level class: usually you should stick to the Box class.
 */
public final class CapitulosCursor extends Cursor<Capitulos> {
    @Internal
    static final class Factory implements CursorFactory<Capitulos> {
        @Override
        public Cursor<Capitulos> createCursor(Transaction tx, long cursorHandle, BoxStore boxStoreForEntities) {
            return new CapitulosCursor(tx, cursorHandle, boxStoreForEntities);
        }
    }

    private static final Capitulos_.CapitulosIdGetter ID_GETTER = Capitulos_.__ID_GETTER;


    private final static int __ID_titulo = Capitulos_.titulo.id;
    private final static int __ID_comentario = Capitulos_.comentario.id;
    private final static int __ID_livroId = Capitulos_.livroId.id;

    public CapitulosCursor(Transaction tx, long cursor, BoxStore boxStore) {
        super(tx, cursor, Capitulos_.__INSTANCE, boxStore);
    }

    @Override
    public final long getId(Capitulos entity) {
        return ID_GETTER.getId(entity);
    }

    /**
     * Puts an object into its box.
     *
     * @return The ID of the object within its box.
     */
    @Override
    public final long put(Capitulos entity) {
        ToOne<Livro> livro = entity.getLivro();
        if(livro != null && livro.internalRequiresPutTarget()) {
            Cursor<Livro> targetCursor = getRelationTargetCursor(Livro.class);
            try {
                livro.internalPutTarget(targetCursor);
            } finally {
                targetCursor.close();
            }
        }
        String titulo = entity.getTitulo();
        int __id1 = titulo != null ? __ID_titulo : 0;
        String comentario = entity.getComentario();
        int __id2 = comentario != null ? __ID_comentario : 0;

        long __assignedId = collect313311(cursor, entity.getId(), PUT_FLAG_FIRST | PUT_FLAG_COMPLETE,
                __id1, titulo, __id2, comentario,
                0, null, 0, null,
                __ID_livroId, entity.getLivro().getTargetId(), 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0);

        entity.setId(__assignedId);

        attachEntity(entity);
        return __assignedId;
    }

    private void attachEntity(Capitulos entity) {
        // Transformer will create __boxStore field in entity and init it here:
        // entity.__boxStore = boxStoreForEntities;
    }

}
