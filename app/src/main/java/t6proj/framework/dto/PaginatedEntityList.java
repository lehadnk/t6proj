package t6proj.framework.dto;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;

import java.util.List;

public class PaginatedEntityList<T> implements PaginatedEntityListInterface<T> {
    private final List<T> list;
    private final int currentPage;
    private final int totalPages;

    public PaginatedEntityList(List<T> list, int currentPage, int totalPages)
    {
        this.list = list;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    @Override
    public List<T> getEntities() {
        return this.list;
    }

    @Override
    public int getCurrentPage() {
        return this.currentPage;
    }

    @Override
    public int getTotalPages() {
        return this.totalPages;
    }
}
