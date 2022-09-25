package com.github.org.todaybread.todaybread.config.graphql;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.web.multipart.MultipartFile;

class MultipartVariableMapper {

    private static final Pattern PERIOD = Pattern.compile("\\.");

    private static final Mapper<Map<String, Object>> MAP_MAPPER =
        new Mapper<Map<String, Object>>() {
            @Override
            public Object set(Map<String, Object> location, String target, MultipartFile value) {
                return location.put(target, value);
            }

            @Override
            public Object recurse(Map<String, Object> location, String target) {
                return location.get(target);
            }
        };
    private static final Mapper<List<Object>> LIST_MAPPER =
        new Mapper<List<Object>>() {
            @Override
            public Object set(List<Object> location, String target, MultipartFile value) {
                return location.set(Integer.parseInt(target), value);
            }

            @Override
            public Object recurse(List<Object> location, String target) {
                return location.get(Integer.parseInt(target));
            }
        };

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void mapVariable(String objectPath, Map<String, Object> variables,
        MultipartFile part) {
        String[] segments = PERIOD.split(objectPath);

        if (segments.length < 2) {
            throw new RuntimeException("object-path in map must have at least two segments");
        } else if (!"variables".equals(segments[0])) {
            throw new RuntimeException("can only map into variables");
        }

        Object currentLocation = variables;
        for (int i = 1; i < segments.length; i++) {
            String segmentName = segments[i];
            Mapper mapper = determineMapper(currentLocation, objectPath, segmentName);

            if (i == segments.length - 1) {
                if (null != mapper.set(currentLocation, segmentName, part)) {
                    throw new RuntimeException("expected null value when mapping " + objectPath);
                }
            } else {
                currentLocation = mapper.recurse(currentLocation, segmentName);
                if (null == currentLocation) {
                    throw new RuntimeException(
                        "found null intermediate value when trying to map " + objectPath);
                }
            }
        }
    }

    private static Mapper<?> determineMapper(
        Object currentLocation, String objectPath, String segmentName) {
        if (currentLocation instanceof Map) {
            return MAP_MAPPER;
        } else if (currentLocation instanceof List) {
            return LIST_MAPPER;
        }

        throw new RuntimeException(
            "expected a map or list at " + segmentName + " when trying to map " + objectPath);
    }

    interface Mapper<T> {

        Object set(T location, String target, MultipartFile value);

        Object recurse(T location, String target);
    }
}