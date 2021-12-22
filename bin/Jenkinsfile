Map customProps =
    [
        pipelineTriggers: [cron('H 2 * * *')],
    ]
Closure catchBody = { e ->
    List recipients = ['Djavid.shayegan@ascension.org, Djohny.vattathara@ascension.org']
    String zipName = 'Case_Tracker_Test_Automation.zip'
    String projectZip = "$env.WORKSPACE/$zipName"
    // Zip up current workspace
    zip zipFile: projectZip, dir: "$env.WORKSPACE/test-output"
    //Advanced Email options
    ascNotify.sendEmail([
        to:                 recipients.join(','),
        attachmentsPattern: "**/$zipName",
        buildStatus:        'FAILED',
        custMsg:            e.message, // Set custMsg to the error message
    ])
    throw e
}
Map body = [catchBody: catchBody]
ascQaPipeline('ios', customProps, body)