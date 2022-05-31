package objects;

import io.fries.koans.Koan;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

@SuppressWarnings("all")
class InterfacesKoans {

    interface Action {
        String description();
    }

    @Koan
    void an_anonymous_instance_can_be_created_from_an_interface() {
        final Action action = new Action() {
            @Override
            public String description() {
                return "Anonymous";
            }
        };

        assertThat(action.description()).isEqualTo(__);
    }

    class LoginAction implements Action {

        @Override
        public String description() {
            return "Login";
        }
    }

    @Koan
    void an_interface_implementation_must_implement_its_behaviors() {
        LoginAction loginAction = new LoginAction();

        assertThat(loginAction.description()).isEqualTo(__);
    }

    interface DefaultAction {

        default String description() {
            return "Default";
        }
    }

    @Koan
    void an_interface_can_implement_default_behaviors() {
        DefaultAction defaultAction = new DefaultAction() {
        };

        assertThat(defaultAction.description()).isEqualTo(__);
    }

    class Logout implements DefaultAction {
    }

    @Koan
    void a_default_behavior_does_not_necessarily_need_to_be_reimplemented() {
        Logout logout = new Logout() {
        };

        assertThat(logout.description()).isEqualTo(__);
    }

    class Walk implements DefaultAction {

        @Override
        public String description() {
            return "Walk";
        }
    }

    @Koan
    void a_default_behavior_can_be_overridden() {
        Walk walk = new Walk() {
        };

        assertThat(walk.description()).isEqualTo(__);
    }

    interface JobOffer {
        default String description() {
            return "Job offer";
        }
    }

    class ConflictingImplementation implements DefaultAction, JobOffer {

        @Override
        public String description() {
            return "Conflicting method declaration";
        }
    }

    @Koan
    void a_method_must_be_overridden_when_it_has_multiple_conflicting_declarations() {
        ConflictingImplementation conflictingImplementation = new ConflictingImplementation();

        assertThat(conflictingImplementation.description()).isEqualTo(__);
    }
}
