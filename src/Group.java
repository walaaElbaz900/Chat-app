interface Group {
    String getGroupType();
}

class PublicGroup implements Group {
    @Override
    public String getGroupType() {
        return "Public Group";
    }
}

class PrivateGroup implements Group {
    @Override
    public String getGroupType() {
        return "Private Group";
    }
}

class GroupFactory {
    public static Group createGroup(String type) {
        switch (type.toUpperCase()) {
            case "PUBLIC": return new PublicGroup();
            case "PRIVATE": return new PrivateGroup();
            default: throw new IllegalArgumentException("Unknown group type: " + type);
        }
    }
}
