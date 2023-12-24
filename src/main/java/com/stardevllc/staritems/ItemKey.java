package com.stardevllc.staritems;

import java.util.Objects;

public class ItemKey implements Comparable<ItemKey> {
    private final String namespace;
    private String key;

    public ItemKey(String namespace, String key) {
        this.namespace = namespace.toLowerCase();
        this.key = key.toLowerCase();
    }

    public String getNamespace() {
        return namespace;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key.toLowerCase();
    }

    @Override
    public String toString() {
        return namespace + ":" + key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ItemKey itemKey = (ItemKey) o;

        if (!Objects.equals(namespace, itemKey.namespace)) {
            return false;
        }
        return Objects.equals(key, itemKey.key);
    }

    @Override
    public int hashCode() {
        int result = namespace != null ? namespace.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(ItemKey o) {
        return toString().compareTo(o.toString());
    }
}
