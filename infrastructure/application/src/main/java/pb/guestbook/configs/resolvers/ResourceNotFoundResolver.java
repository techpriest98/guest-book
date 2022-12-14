package pb.guestbook.configs.resolvers;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

public class ResourceNotFoundResolver implements ResourceResolver {

    private static final String[] RESOURCE_TYPES = {".js", ".css", ".html", ".woff", ".woff2", ".ttf", ".eot", ".svg"};

    @Override
    public Resource resolveResource(
        HttpServletRequest request,
        @Nonnull String requestPath,
        @Nonnull List<? extends Resource> locations,
        @Nonnull ResourceResolverChain chain
    ) {
        if (isResourceRequest(requestPath)) {
            return chain.resolveResource(request, requestPath, locations);
        } else {
            throw new ResourceNotFoundException(format("Resource %s is not supported", requestPath));
        }
    }

    @Override
    public String resolveUrlPath(
        @Nonnull String resourcePath,
        @Nonnull List<? extends Resource> locations,
        ResourceResolverChain chain
    ) {
        return chain.resolveUrlPath(resourcePath, locations);
    }

    private boolean isResourceRequest(String path) {
        return Arrays.stream(RESOURCE_TYPES).anyMatch(path::endsWith);
    }
}
