package org.geekhub.example.entity;

public record FileInfo(String name, String link) {

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        FileInfo that = (FileInfo) obj;
        return this.name.equals(that.name) && (this.link.equals(that.link));
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Name: %s%nLink: %s", name, link);
    }
}
