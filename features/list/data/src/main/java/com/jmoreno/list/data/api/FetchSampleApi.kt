package com.jmoreno.list.data.api

import com.jmoreno.list.data.IFetchApi
import com.jmoreno.list.data.models.FeedNetworkItem

class FetchSampleApi : IFetchApi {
    override suspend fun fetchJson(): Result<List<FeedNetworkItem>> {
        return Result.success(
                listOf(
                    FeedNetworkItem(id = 1, description =  "Rebel Forces spotted on Hoth. Quell their rebellion for the Empire.", title =  "Stop Rebel Forces",
                        timestamp = "2015-06-18T17:02:02.614Z", image = "https://raw.githubusercontent.com/phunware-services/dev-interview-homework/master/Images/Battle_of_Hoth.jpg",
                        date = "2015-06-18T23:30:00.000Z",
                        locationline1 = "Hoth",
                        phone = null,
                        locationline2 = "Anoat System"
                    ),
                    FeedNetworkItem(id = 8, description =  "In a Theed hangar bay, Darth Maul (an apprentice of the Sith Lord Sidious) " +
                            "has been engaging in combat with the two Jedi, using a double-bladed lightsaber." +
                            " The battle moves from the hangar, across a series of catwalks, to the Theed Generator Complex.",
                        title=  "Duel of the Fates",
                        timestamp = "2015-06-26T04:09:30.337Z",
                        image = null,
                        phone = "1 (800) 786-2430",
                        date = "2015-12-26T03:15:00.000Z",
                        locationline1 = "Naboo",
                        locationline2 = "Naboo System"
                        ),
                )
            )
    }
}
