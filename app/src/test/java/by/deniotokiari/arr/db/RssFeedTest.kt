package by.deniotokiari.arr.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import by.deniotokiari.arr.db.dao.RssFeedDao
import by.deniotokiari.arr.db.entity.RssFeed
import by.deniotokiari.arr.getValueBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.*

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class RssFeedTest : RoomDbBaseTest() {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var dao: RssFeedDao

    @Before
    fun init() {
        dao = db.rssFeedDao()
    }

    @Test
    fun create() {
        val feed = RssFeed("Test Feed", "IT", "habr.com")

        val id: Long = dao.insert(feed)

        Assert.assertNotNull(id)

        val ids: LongArray = dao.insert(ArrayList<RssFeed>(Arrays.asList(feed, feed, feed)))

        Assert.assertNotNull(ids)
        Assert.assertEquals(3, ids.size)
    }

    @Test
    fun read() {
        val feed = RssFeed("Test Feed", "IT", "habr.com")
        dao.insert(ArrayList<RssFeed>(Arrays.asList(feed, feed, feed)))

        var feeds: List<RssFeed>? = dao.all().getValueBlocking()

        Assert.assertNotNull(feeds)
        Assert.assertEquals(3, feeds?.size)

        feeds = dao.all(2).getValueBlocking()

        Assert.assertNotNull(feeds)
        Assert.assertEquals(2, feeds?.size)
    }

    @Test
    fun update() {

    }

    @Test
    fun delete() {

    }

}