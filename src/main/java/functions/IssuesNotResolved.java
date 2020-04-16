package functions;

import models.HealthIssue;
import models.Post;
import models.PostType;
import models.Status;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IssuesNotResolved {
    public static Function<List<Post>,List<Post>> issuesNotResolved =
            (posts) -> posts.stream()
                    .filter(x->x instanceof HealthIssue)
                    .filter(x->((HealthIssue) x).getStatus().equals(Status.PENDING) || (((HealthIssue) x).getStatus().equals(Status.IN_CONSULTATION)))
                    .collect(Collectors.toList());

}
