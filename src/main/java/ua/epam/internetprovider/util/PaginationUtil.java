package ua.epam.internetprovider.util;

public class PaginationUtil {
    public static final int SUBSCRIBER_TARIFFS_PER_PAGE = 3;
    public static final int ADMIN_LIST_TARIFFS_PER_PAGE = 7;
    public static final int ADMIN_SUBSCRIBERS_PER_PAGE = 7;

    private PaginationUtil() {
    }

    public static int getPagesCount(int recordsCount, int recordsPerPage) {
        return recordsCount % recordsPerPage == 0 ?
                (recordsCount / recordsPerPage)
                :
                (recordsCount / recordsPerPage + 1);
    }
}
