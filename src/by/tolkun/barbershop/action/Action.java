package by.tolkun.barbershop.action;

import by.tolkun.barbershop.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Action {
    private String name;

    private Set<Role> allowRoles;

    public Action(){
        allowRoles = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(final String inputName) {
        name = inputName;
    }

    public Set<Role> getAllowRoles(){
        return allowRoles;
    }

    abstract public Action.Forward execute(final HttpServletRequest request,
                                        final HttpServletResponse response);

    public static class Forward {
        private String value;
        private boolean redirect;
        private Map<String, Object> attributes = new HashMap<>();

        public Forward(final String inputValue, final boolean inputRedirect) {
            value = inputValue;
            redirect = inputRedirect;
        }

        public Forward(final String value){
            this(value,true);
        }

        public String getValue() {
            return value;
        }

        public void setValue(final String value) {
            this.value = value;
        }

        public boolean isRedirect() {
            return redirect;
        }

        public void setRedirect(final boolean redirect) {
            this.redirect = redirect;
        }

        public Map<String, Object> getAttributes() {
            return attributes;
        }
    }
}
