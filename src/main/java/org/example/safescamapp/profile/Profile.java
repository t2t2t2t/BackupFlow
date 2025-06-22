package org.example.safescamapp.profile;

public class Profile {
    private int id;
    private String name;
    private String sourcePath;
    private String destinationPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    public boolean isFilterExtension() {
        return isFilterExtension;
    }

    public void setFilterExtension(boolean filterExtension) {
        isFilterExtension = filterExtension;
    }

    public String getFilterExtension() {
        return filterExtension;
    }

    public void setFilterExtension(String filterExtension) {
        this.filterExtension = filterExtension;
    }

    public boolean isFilterMod() {
        return isFilterMod;
    }

    public void setFilterMod(boolean filterMod) {
        isFilterMod = filterMod;
    }

    public String getFilterMod() {
        return filterMod;
    }

    public void setFilterMod(String filterMod) {
        this.filterMod = filterMod;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    private boolean isFilterExtension;
    private String filterExtension;
    private boolean isFilterMod;
    private String filterMod;
    private Integer timer;

    public Profile(int id, String name, String sourcePath, String destinationPath, boolean isFilterExtension, String filterExtension, boolean isFilterMod, String filterMod, Integer timer) {
        this.id = id;
        this.name = name;
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
        this.isFilterExtension = isFilterExtension;
        this.filterExtension = filterExtension;
        this.isFilterMod = isFilterMod;
        this.filterMod = filterMod;
        this.timer = timer;
    }
}
