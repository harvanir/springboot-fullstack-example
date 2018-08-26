package org.harvan.example.fullstack.library;

import org.mapstruct.factory.Mappers;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (12 Aug 2018)
 */
public class Mapper {
    public static final BeanMapper INSTANCE = Mappers.getMapper(BeanMapper.class);

    private Mapper() {
        // hide
    }
}