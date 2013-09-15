package llc.web.scheduler.importing



import grails.test.mixin.*
import spock.lang.*

@TestFor(RawCalendarDomainEventController)
@Mock(RawCalendarDomainEvent)
class RawCalendarDomainEventControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.rawCalendarDomainEventInstanceList
            model.rawCalendarDomainEventInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.rawCalendarDomainEventInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def rawCalendarDomainEvent = new RawCalendarDomainEvent()
            rawCalendarDomainEvent.validate()
            controller.save(rawCalendarDomainEvent)

        then:"The create view is rendered again with the correct model"
            model.rawCalendarDomainEventInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            rawCalendarDomainEvent = new RawCalendarDomainEvent(params)

            controller.save(rawCalendarDomainEvent)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/rawCalendarDomainEvent/show/1'
            controller.flash.message != null
            RawCalendarDomainEvent.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def rawCalendarDomainEvent = new RawCalendarDomainEvent(params)
            controller.show(rawCalendarDomainEvent)

        then:"A model is populated containing the domain instance"
            model.rawCalendarDomainEventInstance == rawCalendarDomainEvent
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def rawCalendarDomainEvent = new RawCalendarDomainEvent(params)
            controller.edit(rawCalendarDomainEvent)

        then:"A model is populated containing the domain instance"
            model.rawCalendarDomainEventInstance == rawCalendarDomainEvent
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            status == 404

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def rawCalendarDomainEvent = new RawCalendarDomainEvent()
            rawCalendarDomainEvent.validate()
            controller.update(rawCalendarDomainEvent)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.rawCalendarDomainEventInstance == rawCalendarDomainEvent

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            rawCalendarDomainEvent = new RawCalendarDomainEvent(params).save(flush: true)
            controller.update(rawCalendarDomainEvent)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/rawCalendarDomainEvent/show/$rawCalendarDomainEvent.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            status == 404

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def rawCalendarDomainEvent = new RawCalendarDomainEvent(params).save(flush: true)

        then:"It exists"
            RawCalendarDomainEvent.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(rawCalendarDomainEvent)

        then:"The instance is deleted"
            RawCalendarDomainEvent.count() == 0
            response.redirectedUrl == '/rawCalendarDomainEvent/index'
            flash.message != null
    }
}
