package pl.edu.wat.groomer_queue.service;


import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.groomer_queue.repository.DogRepository;
import pl.edu.wat.groomer_queue.repository.OwnerRepository;

@Service
@Slf4j
public class ScriptService {
    private final DogRepository dogRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public ScriptService(DogRepository dogRepository, OwnerRepository ownerRepository) {
        this.dogRepository = dogRepository;
        this.ownerRepository = ownerRepository;
    }

    public String exec(String script) {
        try (Context context = Context.newBuilder("js")
                .allowAllAccess(true)
                .build()) {
            var bindings = context.getBindings("js");
            bindings.putMember("dogRepository", dogRepository);
            bindings.putMember("ownerRepository", ownerRepository);
            return context.eval("js", script).toString();
        } catch (PolyglotException e) {
            log.error("Error executing", e);
            return e.getMessage() + "\n" + e.getSourceLocation().toString();
        }
    }
}
