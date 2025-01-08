Feature: Reviewing a Ph.D. Thesis
    Every PhD thesis review has some recurrent steps

    Scenario: A reviewer, being an expert on the field, should be cited somewhere
        Given A PhD thesis to review
        And a reviewer Bruel
        Then The thesis should cite the reviewer's work
            # => brew install pdfgrep 
            # => pdfgrep -Ri keyword /some/directory 