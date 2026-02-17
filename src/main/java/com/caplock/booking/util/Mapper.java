package com.caplock.booking.util;

import java.lang.reflect.Field;

public class Mapper {
    public static <T> T mapDtoToDao(Object dto, Class<T> daoClass) {
        if (dto == null) {
            return null;
        }

        try {
            T dao = daoClass.getDeclaredConstructor().newInstance();
            // Copy fields from dto to dao using reflection or manual mapping
            copyProperties(dto, dao);
            return dao;
        } catch (Exception e) {
            throw new RuntimeException("Failed to map DTO to DAO", e);
        }
    }

    public static <T> T mapDaoToDto(Object dao, Class<T> dtoClass) {
        if (dao == null) {
            return null;
        }

        try {
            T dto = dtoClass.getDeclaredConstructor().newInstance();
            // Copy fields from dao to dto using reflection or manual mapping
            copyProperties(dao, dto);
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Failed to map DAO to DTO", e);
        }
    }
    private static void copyProperties(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }

        Field[] sourceFields = source.getClass().getDeclaredFields();

        for (Field sourceField : sourceFields) {
            try {
                sourceField.setAccessible(true);
                Field targetField = target.getClass().getDeclaredField(sourceField.getName());
                targetField.setAccessible(true);

                Object value = sourceField.get(source);
                targetField.set(target, value);
            } catch (NoSuchFieldException e) {
                // Field doesn't exist in target, skip it
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to copy property: " + sourceField.getName(), e);
            }
        }
    }
}

